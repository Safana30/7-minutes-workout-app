package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val MTERIC_UNITS_VIEW="METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW="US_UNITS_VIEW"
    }

    private var currentVisibleView:String=
        MTERIC_UNITS_VIEW //A Variable to hold a value to make a selected view visible

    private var binding:ActivityBmiBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId:Int ->
            if(checkedId==R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {

           calculateUnits()
        }
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView= MTERIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility=View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.GONE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }

    private fun makeVisibleUsUnitsView(){
        currentVisibleView= US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.GONE
        binding?.tilMetricUnitHeight?.visibility=View.GONE
        binding?.tilUsMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.VISIBLE


        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etMetricUsUnitHeightFeet?.text!!.clear()
        binding?.etMetricUsUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }


    private fun displayBMIResult(bmi:Float){
        val bmiLabel:String
        val bmiDescription:String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="Very severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself!Eat more!"
        }else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="Severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself!Eat more!"
        }else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0){
            bmiLabel="Underweight"
            bmiDescription="Oops! You really need to take better care of yourself!Eat more!"
        }else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<=0){
            bmiLabel="Normal"
            bmiDescription="Congratulations! You are in a good shape!"
        }else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0){
            bmiLabel="Overweight"
            bmiDescription="Oops! You really need to take better care of yourself!Workout!"
        }else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f)<=0){
            bmiLabel="Obese Class|(Moderately obese)"
            bmiDescription="Oops! You really need to take better care of yourself!Workout!"
        }else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f)<=0){
            bmiLabel="Obese Class||(Severely obese)"
            bmiDescription="OMG! You are in a very dangerous condition!Act Now!"
        }else{
            bmiLabel="Obese Class|||(Very severely obese)"
            bmiDescription="OMG! You are in a very dangerous condition!Act Now!"

        }

        val bmiValue=BigDecimal(bmi.toDouble())
            .setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility=View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmiLabel
        binding?.tvBMIDescription?.text=bmiDescription


    }
    private fun validateMetricUnits():Boolean{
        var isValid=true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView== MTERIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val weightValue:Float=binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi=weightValue/(heightValue*heightValue)

                displayBMIResult(bmi)

            }else{
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            if(validateUsUnits()){
                val usUnitHeightValueFeet:String=
                    binding?.etMetricUsUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch:String=
                    binding?.etMetricUsUnitHeightInch?.text.toString()
                val usUnitWeightValue:Float=
                    binding?.etUsMetricUnitWeight?.text.toString().toFloat()

                val heightValue=
                    usUnitHeightValueInch.toFloat()+usUnitHeightValueFeet.toFloat()*12

                val bmi=703*(usUnitWeightValue/(heightValue*heightValue))

                displayBMIResult(bmi)
            }else {
                Toast.makeText(this@BMIActivity, "Please enter valid values", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun validateUsUnits():Boolean{
        var isValid=true

       when{
           binding?.etUsMetricUnitWeight?.text.toString().isEmpty()->{
               isValid=false
           }
           binding?.etMetricUsUnitHeightFeet?.text.toString().isEmpty()->{
               isValid=false
           }
           binding?.etMetricUsUnitHeightInch?.text.toString().isEmpty()->{
               isValid=false
           }

       }
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}
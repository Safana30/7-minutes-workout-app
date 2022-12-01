package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList=ArrayList<ExerciseModel>()
        val jumpingJacks=ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val crunch=ExerciseModel(
            2,"Crunch",R.drawable.ab_crunch,false,false
        )
        exerciseList.add(crunch)

        val highKnees=ExerciseModel(
            3,"High Knees",R.drawable.high_knees,false,false
        )
        exerciseList.add(highKnees)

        val lunge=ExerciseModel(
            4,"Lunge",R.drawable.lunge,false,false
        )
        exerciseList.add(lunge)

        val plank=ExerciseModel(
            5,"Plank",R.drawable.plank,false,false
        )
        exerciseList.add(plank)

        val pushupAndRotation=ExerciseModel(
            6,"Pushup And Rotation",R.drawable.pushup_and_rotation,false,false
        )
        exerciseList.add(pushupAndRotation)

        val pushups=ExerciseModel(
            7,"Pushups",R.drawable.pushups,false,false
        )
        exerciseList.add(pushups)

        val sidePlank=ExerciseModel(
            8,"Side Plank",R.drawable.side_plank,false,false
        )
        exerciseList.add(sidePlank)

        val squat=ExerciseModel(
            9,"Squat",R.drawable.squat,false,false
        )
        exerciseList.add(squat)

        val stepUp=ExerciseModel(
            10,"StepUp",R.drawable.stepup,false,false
        )
        exerciseList.add(stepUp)

        val tricepsDip=ExerciseModel(
            11,"Tricep Dip",R.drawable.triceps_dip,false,false
        )
        exerciseList.add(tricepsDip)

        val wallSit=ExerciseModel(
            12,"Wall Sit",R.drawable.wall_sit,false,false
        )
        exerciseList.add(wallSit)





























        return exerciseList
    }
}
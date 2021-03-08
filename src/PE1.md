## Spring 2021
# COS 126 Programming Exam 1

---

## Instructions

**Before the exam.**
You may read this page of instructions before the exam begins. But do not start *(even by reading the next page)* until you are instructed to do so.

**Overview.** 
You will have 50 minutes to complete a few programming tasks and then upload a Java file that contains your solution. Download the project zip file, which includes all the files you will need, from the 
[Exams tab](https://www.cs.princeton.edu/courses/archive/spring21/cos126/exams.html) 
of the course web page.
You may download this file before the exam begins.

**Resources.** 
You may use your book, your notes, your code from programming assignments and precepts, the code on the COS 126 course website, the booksite, and you may read Ed. No form of communication is permitted (e.g., talking, texting, etc.) during the exam, except with course staff.

**Do not discuss.**
Due to various conflicts, and multiple class times, some of your peers will take the exam at a different time. Do not discuss exam contents with anyone *(not even other students that you know already took the exam!)* until after the graded exams are returned. 

**Honor Code pledge.**
Before submitting your solution, you must “electronically sign” the honor code in the obvious comment at the end of your Java file, by retyping the pledge and then your name. 

**Submissions.**
Submit your work using the `Submit` link on
the **Exams** tab of the course [web page](https://www.cs.princeton.edu/courses/archive/spring21/cos126/exams.html). 

**Grading.**
Your program will be graded on mainly on correctness. You will lose a substantial fraction of your overall grade if your program does not compile, or if it crashes on typical inputs. Clarity (including comments), design, and efficiency are secondary concerns for this timed exam.

---
<div style="page-break-after: always;"></div>

## Background

You are an engineer at a startup company called Zoom, which creates software for online video meetings. One feature especially useful in classroom settings is called *breakout rooms*. This feature allows teachers to divide a large class into smaller groups, each of which meets independently in different “rooms.” 

**Greedy Algorithm.**
Another engineer has already developed an algorithm to assign students to rooms in a “greedy” fashion, as follows. Suppose the room capacity is *N*. The greedy algorithm places the first *N* students in Room 1, and the next *N* students in Room 2, and so forth, until there are no students left. Unfortunately there are some drawbacks to this algorithm, and your manager has asked you to provide some alternatives. But first you need to write some helper functions to get you started.

## Getting Started

The only file you need to edit is `ZoomRooms.java`, and it contains some code to get you started.

**Step 0**.
Notice that if you compile and run the code it already does something:

```
javac-introcs ZoomRooms.java
java-introcs ZoomRooms greedy 3

Room: 1
----------
```

There are two command line arguments given here (the string `greedy` and the number 3. The first one tells tells the program which assignment algorithm to use (`greedy` in this case, and the other two options are `robin` and `random`). The second argument is the maximum number of students the teacher wants in each room (3 in this case).

As a reminder: the code you submit in the end should *still* compile, and also run without errors for all inputs that match the program specications.

## The Exam

**Step 1** (3 pts).
The starting code you ran in the last step only prints out the first room. Modify the `printRooms` function so that it prints out all `numRooms` rooms, by adding a loop. If your changes are successful, when you compile and run it, `ZoomRooms` should now print out the headers for Rooms 1 and 2. But there are still no student names printed yet, which will be addressed in the next step.

**Step 2** (5 pts).
Next you will modify the `printRoom` function so that it prints out names of students in a room, not just the room number. This function has an argument `room` that says which room it should print out. It receives two other arguments that are *parallel arrays:* `studentRooms` and `studentNames`. The first array says which room each student is in, while the second says their names. Here's an example of what those arrays might look like in this program:

index  | studentRooms | studentNames
------ | ------------ | ------------
0      | 1            | Ava
1      | 1            | Ben
2      | 1            | Carol
3      | 2            | Dan
4      | 2            | Emma

Write a loop that considers each student in turn, and prints out the names of those students whose entry in `studentRooms` match the `room` argument. For the example arrays shown above, if `room == 2`, then `printRoom` would output the names Dan and Emma under the dashed line. 
So running the program again should produce this:

```
java-introcs ZoomRooms greedy 3

Room: 1
----------
Ava
Ben
Carol

Room: 2
----------
Dan
Emma
```

**Step 3** (4 pts).
Your starting code has a function `readNames` that returns an array of student names. It is called by the `main` function to get this list of names. But the temporary code you were given always returns the same five names shown above (Ava, Ben, ...). Now you will change this function so that it reads an integer *N* &ge; 0, followed by *N* names, reading from `StdIn`. You can assume that after *N*, the input will always be exactly *N* names, each on one line that could be read using the function `StdIn.readString`. There are several example names files in the directory that conform to this specification.

Your function should return an array containing those *N* student names. For example, if you provide the file `names3.txt` on `StdIn` your version of `readNames` would return the three names in that text file (which happen to be first three above), and the output would be:

```
java-introcs ZoomRooms greedy 3 < names3.txt

Room: 1
----------
Ava
Ben
Carol

Room: 2
----------
```

You can try it out with various names files in your directory, but note that there will always be two rooms printed out, no matter how many students you read in. That will be fixed in the next step.

**Step 4** (2 pts).
Now you have a simple math problem. Recall that the second command line argument specifies the room size (the maximum capacity for each room). For example, it's 3 in the command shown above, which means that Ava, Ben and Carol can all fit in Room 1. But how did we get that the number of rooms should be 2? 

The function `roomsNeeded` receives two arguments: `numStudents` (the total number of students) and `roomSize` (the size of each room, as specified on the command line).
This function should compute the number of rooms needed, but as provided it always just returns 2, no matter what. Instead change it to return `numStudents` divided by `roomSize`, **rounding up** to an integer in cases where it does not divide evenly. Try it out using some of the sample input files with different numbers of students, to convince yourself it works properly.

**Step 5** (5 pts).
One of the drawbacks of the “greedy” algorithm for assigning students to rooms is that you might end up with a room with only one student alone, at the end of the list. A way to (mostly) avoid this problem is to assign the students in “round robin” order: the first student in Room 1, the second student in Room 2, third in Room 3, and so forth up to the *N*-th student in Room N. Next it wraps around: the *(N+1)*-th student goes in Room 1, and the *(N+2)*-th in Room 2, etc. Your next task is to implement this algorithm in the function `assignRobin`. After you have it coded up, you can try it out like this:

```
java-introcs ZoomRooms robin 3 < names5.txt

Room: 1
----------
Ava
Carol
Emma

Room: 2
----------
Ben
Dan
```

Note that with this algorithm it is still *possible* to end up with one student alone in a room, in some hard cases, but it is less likely overall.

## Bonus Challenge

**Step 6** (1 pt).
Congratulations if you made it this far! This last step is hard one, to provide an extra challenge for those of you who have sufficient time for it.

Your goal is to implement a third assignment algorithm in the function `assignRandom` that places students in rooms randomly, with equal probability of ending up in any room. Note that rooms still have capacity `roomSize` so you need to keep track of that somehow. As a hint, here are two possible general strategies for accomplishing this goal:

* As you assign students to rooms (randomly) you could maintain an array that tracks how many are in each room, and avoid assigning students to rooms that are full.
* Alternately, you could shuffle the list of students randomly; and then assign them using, say, the “round robin” approach.

Either way would work, and you might also think of other strategies. Regardless, the details are up to you. Obviously if you implement this algorithm it should generally give different answers each time you run it on the same input, which could be done for example like this:

```
java-introcs ZoomRooms random 3 < names5.txt
```

## Finishing Up
Don't forget to write and electronically sign the Honor Code pledge in the comment at the end of your Java file, before uploading it.
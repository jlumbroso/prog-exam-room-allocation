# Room Allocation — Java Programming Exam

This is a draft of the exam.

## Ideas:

### Workflow
Give “greedy” 
Give default static assignment of student names
They can first implement output and main that calls greedy and then calls print output.
Provide “title printing” as a helper function (to avoid autograder problems with like the number of stars)?


### Helpers function that could be provided

```java
void printRoomTitle(int room) {
   String title = "Room " + room;

   StdOut.println(title);

   // the Java method <str>.repeat(<number>) will produce a string made up of
   // <number> consecutive copies of the string <str>
   StdOut.println("-".repeat(s.length));

   StdOut.println();
}
```

### Narratives

- Precepts narrative: Assign students into precepts
- Zoom narrative: Assign students into zoom rooms


### Function Ideas:

Input/output methods:
- Read in student names from StdIn.
- Print out assignments at the end (by room).

Assignment methods:
- Greedy assignment - worst load balance because it leaves last room most empty.
- Round robin assignment: 0,1,2,0,1,2 (load balanced)
- Random assignment of students to rooms. Roll dice, check to see if room is full. If so, roll again. (Requires array that counts students/room.)

Heuristic methods:
- Given a list of students (and thus the size of that list) and a *max per breakout room* parameter, figure out how many rooms are needed


## Open Questions

- Zero-indexing?

- In what guise do we have the exam include *testing* or *unit-testing*, if at all?

- How do we store user-room assignments? Do we need to store them or compute them or not?

- To keep track of room capacity, an array `capacity` where `capacity[i]` is the number of slots available in room `i`;

    | array name | 0 | 1 | 2 | 3 |
    |------------|---|---|---|---|
    | `capacity` | 3 | 0 | 2 | 1 |

- Parallel arrays? array `students` (or `users`) and array `breakout_rooms`

    | array name | 0    | 1    | 2       | 3    |
    |------------|------|------|---------|------|
    | `students` | Adam | Alan | Jérémie | Sooh |
    | `rooms`    | 1    | 2    | 2       | 1    |


- Output format, see also `printRoomTitle()`:

    ```
    Room 0
    ----------
    Adam
    Sooh

    Room 1
    ----------
    Jérémie
    Alan
    ```

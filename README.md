# Interview Task

Project created for interview purposes based on the description:

    We need an application to sort students by their performance.
    System UI can be written using any framework you are familiar with, but It must allow the user to select a text file with student data, sorting algorithm and display the result in a table with an option to save it to a file.
    As we are not sure how many students will have to be sorted, we would like you to implement Bubble, Heap and Merge sorting algorithms, but enable system design to be easily extendable in future.

    To simplify benchmarking we would like to see a number of records and sorting time.

    Input file example:
    Student1,8.5
    Student2,6.5
    Student3,5.0

# Solution

To make sure that the sorting algorithm base could be easily expanded I have created an abstract layer for ease of
swapping between different algorithms (Basic Strategy pattern).
The 'SortableStudents' class is something like an aggregate that keeps the state of the initial list of students,
selected sorting algorithm, sorted list of students and the sorting calculation time.

## A word from creator

I'm not a frontend developer, so I picked the first web framework that I found.
I tried to stick to hexagonal architecture, maybe it is a bit overkill for the task but still makes it more readable.
Also, I created some simple test cases to test happy path of the task. 

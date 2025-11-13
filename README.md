# CS321-resources
Public resources for CS 321 students. The repository contains three main resources:

 * Code examples that are used in the class notes. These are useful for students to experiment with
   to further their understanding.
 * Starter code and data for projects.
 * Notes for the course.


 The code examples can be converted into markdown (as a single file, suitable for ingestion into AI
 tools) using git2md. Install the command as follows:

 pip install git2md

 Then run the following command in the repository root:
     git2md examples/ --ignore \"*.c\" \"*.h\" \"*.txt\" \"data*\" \"log*\" -o output.md

 This will create a file named output.md containing all the code examples in markdown format.




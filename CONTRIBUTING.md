# Contribution guidelines

Write tests for your code. As a corollary, write testable code. The harder it is to test code, the more likely it is to be difficult to read, and more likely to break in unusual ways. Try breaking the problem down into finer pieces, and write functions that do a single thing rather than performing complex serial or parallel operations within an atomic unit.

If you modify a code path, try to leave it cleaner, more readable, safer, and better tested than how it came to you.

Prefer the least powerful method to accomplish a task.

If the behavior of a function or line of code is not apparent (esp. if it does something complex/tricky) consider (in order of preference):

- writing the implementation more plainly
- renaming functions and variables to better indicate what they do
- writing thorough documentation in comments

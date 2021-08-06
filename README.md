# PayCalculator
Java exercise to calculate costs based on CSV files. Runs on Java 14, development was done in Eclipse.

# Instructions
1. Run program, make sure the files are located with a java package with the name "paymentcalc".
2. You will be prompted with "Please enter a CSV file name to read from", enter a file name then press enter. e.g. "InputFile" as provided.
  a. If this doesn't work it's because the file doesn't exist.
3. You will now be prompted with "Would you like to continue? y/n", there are 3 possible outcomes:
  a. If you type "Y" then hit enter, the program will either create a new file and input the given information, or overwrite a file with the same name with the new information.
  
  b. If you type "N" then hit enter, the program will safely stop.
  
  c. If you do anything else and press enter, the program will loop and you'll need to input either y/n again.

# Assumptions
- The CSV files are only to be processed when given the strict format of fname, lname, salary, super, and initial payment, I've added some handling to process incomplete CSV files but additional or less information that outlined won't process.
- The tester viewing this document will import the git project into a package called "paymentcalc". In order for me to include the package in the Github, I'd need to make a new repository which would remove all evidence of progress.
- It wasn't explicitly mentioned to include it, but I've implemented CSV input and output as there was a line in the specification that danced around it.

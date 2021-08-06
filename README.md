# PayCalculator
Java exercise to calculate costs based on CSV files. Runs on Java 14, development was done in Eclipse.

# Instructions
1. Run program, make sure the files are located with a java package with the name "paymentcalc"
2. You will be prompted with "Please enter a CSV file name to read from", enter a file name then press enter. e.g. "InputFile" as provided.
  a. If this doesn't work it's because the file doesn't exist
3. You will now be prompted with "Would you like to continue? y/n", there are 3 possible outcomes:
  a. If you type "Y" then hit enter, the program will either create a new file and input the given information, or overwrite a file with the same name with the new information
  b. If you type "N" then hit enter, the program will safely stop
  c. If you do anything else and press enter, the program will loop and you'll need to input either y/n again

## Private Investigator Assignment

### Overview of the solution:
This solution initially reads the input.txt file splits each line by empty space and fills the Map data structure used inside the program with key as wordLength an value as List of List of Strings which is to group the sentences by the length of the words since two sentences differ more than one word is not considered to be similar.
Once we group then we are finding if the sentences are differing only by one word and adding it to the set.
After the above steps get completed successfully we are printing the results in the expected format to the output file

**How to run?**

step 1: clone the git repository by running the command **git clone https://github.com/rraviven/PrivateInvestigatorHomeAssignment.git**

step 2: Run the main function inside the file (**PrivateInvestigatorMain**) under src/com/assignment/privateinvestigator/main folder.

**Note:** Input file under src/resources/input folder can be modified to test with different inputs

**1. What can you say about the complexity of your code?**

Currently the complexity of the code is dependent on two main factors which is the number of words in the sentence (word length) and also the number of sentences in each group i.e number of sentences in with same word length


**2. How will your algorithm scale?**

This algorithm is a simple and straight forward solution in case if in future the number of sentences scale to the huge amount then we can utilize the concept of multithreading to do the actual comparison of the sentences or this can be done as a asynchronous process/job


**3. If you had two weeks to do this task, what would you have done differently? What would be better?**

If i had two weeks then I,
1. Could have understood the problem and use case scenarios much better and also about the expected scale in the near future.
2. Could have improved my solution by doing enough research on the string algorithms which might be a good fit for this solution.
3. Could have written the code with proper unit testing
4. Could have done proper error/exception handling using custom exceptions
5. Could have done performance testing by gathering multiple data sets
6. could have spent more time on the design of the solution both high level as well as low level

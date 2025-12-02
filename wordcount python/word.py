from pyspark import SparkContext

# Initialize SparkContext
sc = SparkContext("local", "Word Character Count")

# Sample paragraph (replace this with your input text)
paragraph = """
This is the first line.
And this is the second line.
Here comes the third line.
"""

# Create an RDD (Resilient Distributed Dataset) from the paragraph
rdd = sc.parallelize(paragraph.split("\n"))

# Count the number of lines
num_lines = rdd.count()
print(f"Number of lines: {num_lines}")

# To count the number of characters for each word in the paragraph
# First, split the lines into words and then map each word to its length
word_lengths = rdd.flatMap(lambda line: line.split()) \
                  .map(lambda word: (word, len(word)))

# Collect the results and print
word_lengths_result = word_lengths.collect()
for word, length in word_lengths_result:
    print(f"Word: {word}, Length: {length}")

# Stop the SparkContext when done
sc.stop()


**Hash Part:**
**Hash Calculation:** The hash function uses a simple multiplication hash method, where the hash value is calculated by multiplying the hash value by 33 and then adding the ASCII value of the character. This calculation method generally helps map different strings to different hash values, reducing the probability of hash collisions.
**Handling Hash Collisions:** In a hash table, multiple different keys may map to the same hash value, causing hash collisions. The code uses open addressing, specifically linear probing, to handle hash collisions. After calculating the hash value, if the position in the hash table is already occupied, it continues to probe linearly until it finds an empty slot to insert the new element or encounters an element with the same key, in which case it updates the count. However, efficiency can decrease significantly in the presence of many collisions, so using double hashing to handle collisions might be a solution to improve code efficiency.
**Hash Table Size:** The code uses a fixed-size hash table with a size of 1000 (`private static final int TABLE_SIZE = 1000`).

The implementation of the code is mostly correct. It uses a hash table to count word occurrences and sorts when necessary to get the top 100 words list. My code also handles functionalities like ignoring words from a file and counting total words.

**Issues or Errors:** There are no apparent issues or errors in the code. However, in large text files or with a large number of words, there may be performance issues or a need for further optimization. For example, the sorting algorithm used (heap sort) has a time complexity of O(nlogn), and when dealing with large datasets, the additional space required can also be significant.

**Time Complexity:** The average case time complexity of hash table insertions is O(1), but it can reach O(n) in case of collisions. The time complexity of the sorting algorithm depends on the specific algorithm used; here, it's heap sort with a time complexity of O(nlogn).

**Efficiency in Reading and Managing Words:** Due to the constant time complexity of hash table insertions in the average case, the code efficiently manages words and their counts.

**Efficiency in Getting Top 100 List:** The efficiency of getting the top 100 words list depends on the sorting algorithm used. In this case, heap sort is used, which has a time complexity of O(nlogn), making it acceptable for moderately sized datasets.

**Most Difficult Things to Understand and Implement:** One of the most challenging aspects was optimizing the hash table's performance and handling large-scale datasets efficiently. This involved understanding hash functions, handling hash collisions, dynamically adjusting hash table size, etc. Additionally, implementing and understanding heap sort algorithm was also a challenge.

Through this task, I learned how to use hash tables to count word occurrences and gained insights into hash functions, hash collision handling, sorting algorithms, and their implementations. I also deepened my understanding and practical experience in code optimization and performance tuning.

**BST Part:**
**Getting Top 100 List:** It is achieved by performing an inorder traversal (first traverse the left subtree, then process the current node, and then traverse the right subtree).

The logic of this code is as follows:
step 1. If the current node is null, return the current index.
step 2. Recursively traverse the left subtree and update the index with the returned value.
step 3. Add the current node to the array and update the index.
step 4. Recursively traverse the right subtree and update the index with the returned value.

**Correctness of Implementation:** My code implements a binary search tree and adds word counting functionality. It is correct in basic functionalities, correctly counting occurrences and sorting to output the top 100 words.

**Issues, Errors, or Difficulties to Solve:** There may be potential memory leakage issues in the code, such as not releasing memory occupied by nodes in the `close()` method.

**Time Complexity:** The time complexity of insertion operations depends on the balance of the tree and is O(log n) in the worst case, where n is the number of nodes. Traversal operations (inorder traversal, counting word occurrences) also have a time complexity of O(n), where n is the number of nodes.

**Efficiency in Reading and Managing Words and Counts:** The code efficiently reads files and manages word counts using the properties of a binary search tree for quick insertion and counting operations.

**Efficiency in Getting Top 100 List:** The efficiency of getting the top 100 words list depends on the size and balance of the tree; generally, it is quite efficient.

**Sorting Algorithm Used and Time Complexity:** My code uses heap sort to sort the node array. Heap sort has a time complexity of O(n log n), where n is the number of nodes.

**Most Difficult Things to Understand and Implement:** The most challenging part was understanding how to correctly count word occurrences and output the top 100 words in descending order. Additionally, calculating the maximum depth of a binary search tree was also a challenging aspect.

Through this project, I learned how to implement word counting functionality using a binary search tree and gained an understanding of the advantages and disadvantages of different data structures when solving the same problem. It also helped me enhance my understanding and implementation skills in data structures and algorithms.

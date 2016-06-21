# ExtractiveTextSummarizer
The code takes in an article and returns a summary of about 75 words.
The code uses the following features
1) Similarity between a sentence and the first sentence of the text.
2) Importance to first line of every new paragraph
3) Similarity between sentence and first line of the corresponding paragraph.
4) PageRank algorithm.
5) Importance to sentence containing a quoted text.
6) A lexical database that accounts for synonyms.
7) Chronological sequence.
8) Frequently occuring unigrams and bigrams.

To compute the similarity between two sentences I have used tf-idf cosine similarity method. More info on:-
https://janav.wordpress.com/2013/10/27/tf-idf-and-cosine-similarity/
PageRank refference:-
http://www.aclweb.org/anthology/P04-3020
Lexical database from wordnet.
Feature references 
http://anthology.aclweb.org/C/C08/C08-1124.pdf.

Summarization is of two types. Extractive and Abstractive. Extractive is what I have done and basically ranks individual sentences according to
a specified criteria. Research in Abstractive is in its budding stages with no consolidatory instances of its implementation. It is 
hugely difficult and does not involve any conclusive solution. Please do read about it more.
I checked into various products and pioneers like inShorts have their own manual team to curate and summarize articles while newstand does
extractive.

I also implemented a Naive Bayess learning model to the above algorithm that trains the code to analyze results of an existing database 
and then compute the present summary.


5.2 Naïve Bayesian Classier (NBC)
Naïve Bayesian Classier assumes features are
independent. It learns prior probability and conditional
probability of each feature, and predicts
the class label by highest posterior probability.
Given a feature vector (F1, F2, F3,…, Fn), the
classifier need to decide the label c:
argmax ( | , , ,... ) 1 2 3 n c
c = P c F F F F
By applying Bayesian rule, we have
( , , ,..., )
( ) ( , , ,..., | ) ( | , , ,..., )
1 2 3
1 2 3
1 2 3
n
n
n P F F F F
P c P F F F F c P c F F F F =
Since the denominator does not depend on c and
the values of Fi are given, therefore the denominator
is a constant and we are only interested in
the numerator. As features are assumed independent,

∏=
≈
=
n
i
i
n n
P c P F c
P c F F F F P c P F F F F c
1
1 2 3 1 2 3
( ) ( | )
( | , , ,... ) ( ) ( , , ,... | )
where P(F | c) i is estimated with MLE from
training data with Laplace Smoothing

Given:
L is the set of labeled training examples
U is the set of unlabeled training examples
Loop: until the unlabeled data is exhausted
Train the first classifier C1 (PSVM) on L
Train the second classifier C2 (NBC) on L
For each classifier Ci
Ci labels examples from U
Ci chooses p positive and n negative examples
E from U. These examples have
top classifying confidence.
Ci removes examples E from U
Ci adds examples E with the corresponding
labels to L 

https://www.youtube.com/watch?v=EGKeC2S44Rs

After implementing this I found the results to be pretty erratic and certainly worse than what was achieved before.
(In technical terms, the probability of each sentence to not be included in the summary was either very high or very low).
Owing to the results, I scraped the model completely. If you want I can redo it and show you the results.

I recommend all to test the code and run it for bugs and test it for different articles. A sample input is present in the input.txt file
and the output in the output.txt file. In order to run it, please download an IDE(prefferably NetBeans 8.0.2) and watch the following video
https://netbeans.org/kb/docs/ide/git_nb_ssh_screencast.html.

Thank you!.

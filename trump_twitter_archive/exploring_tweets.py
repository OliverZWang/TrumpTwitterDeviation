import pandas as pd
from pycaret.nlp import *

df = pd.read_csv('trump_20200530.csv')

print("Shape:")
print(df.shape)
print()

print("Head: ")
print(df.head())

# sampling the data to select on ly 1,000 tweets
df = df.sample(50, random_state=493).reset_index(drop=True)
print("Sample df shape: ")
print(df.shape)
print()

nlp = setup(data = df, target = 'text', session_id = 493, custom_stopwords=['rt', 'https', 'http', 'co', 'amp'])
lda = create_model('lda', num_topics = 6, multi_core = True)


lda_results = assign_model(lda)
# f = open('results.txt', 'w')
# print(lda_results[1, 3])
# f.close()
# print(lda_results.head())
print("Got here")
plot_model(lda, plot = 'topic_distribution')
# plot_model(lda, plot = 'trigram', topic_num = 'Topic 1')
# plot_model(plot = 'bigram')
# plot_model(plot = 'trigram')
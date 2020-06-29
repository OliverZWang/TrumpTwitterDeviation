import os
import tweepy
import json


consumer_key = os.environ.get("CONSUMER_KEY")
consumer_secret = os.environ.get("CONSUMER_SECRET")
access_token = os.environ.get("ACCESS_TOKEN")
access_token_secret = os.environ.get("ACCESS_TOKEN_SECRET")

auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth)

trends_new_york = api.trends_place(2459115)

trends_new_york = json.loads(json.dumps(trends_new_york, indent=1))
print("Current trend in New York: ")
for trend in trends_new_york[0]["trends"]:
	print((trend["name"]).strip("#"))



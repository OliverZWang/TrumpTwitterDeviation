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

trends_us = api.trends_place(23424977)
# trends_us = api.trends_place(1)
# api.update_status('Updating using OAuth authentication via Tweepy!')
print(trends_us)
trends_us = json.loads(json.dumps(trends_us, indent=1))
print("Current trend in the United States: ")
for trend in trends_us[0]["trends"]:
	print((trend["name"]).strip("#"))



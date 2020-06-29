import pandas as pd                        
from pytrends.request import TrendReq
pytrend = TrendReq(hl='en-US', tz=360)

pytrend.build_payload(["foobar"], cat=0, timeframe='today 5-y', geo='', gprop='')
# df = pytrend.trending_searches(pn="united_states")

# print(df.head(10))

# df = pytrend.today_searches(pn="US")

# print(df.head(10))

# df = pytrend.top_charts(2019, hl='en-US', tz=300, geo='GLOBAL')
# # print("Top searches in 2019")
# print(df.head())

# df = pytrend.interest_over_time()
# print("Interest over time: ")
# print(df)

kw_list = ["foobar"]
df = pytrend.get_historical_interest(kw_list, year_start=2018, month_start=1, day_start=1, hour_start=0, year_end=2018, month_end=2, day_end=1, hour_end=0, cat=0, geo='', gprop='', sleep=0)
print()
print(df)
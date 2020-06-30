import pandas as pd
from datetime import datetime
import os
from TrendPageScraper import TrendPageScraper



def main():

    mkdir_if_not_exist("trends_data")
    date_range = pd.date_range(start="2016-01-10", end="2020-06-28")
    url_prefix = "https://us.trend-calendar.com/trend/"
    url_suffix = ".html"

    path_prefix = "./trends_data/"
    path_suffix_twitter = "-twitter.csv"
    path_suffix_google = "-google.csv"

    for date in date_range:
        date = extract_date(date)
        # print(date)

        # make the directory for this date
        mkdir_if_not_exist("trends_data/"+date)

        url = url_prefix + date + url_suffix
        # print(url)

        path_twitter = path_prefix + date + "/" + date + path_suffix_twitter
        path_google = path_prefix + date + "/" + date + path_suffix_google
        # print(path_twitter)
        # print(path_google)
        # print()

        scraped_page = TrendPageScraper(url, path_twitter, path_google)





        

    
    # print(extract_date(date_range[0]))

# this function takes a pd datestamp and return the date part as a string
def extract_date(pd_date):
    list = str(pd_date).split()
    return list[0]

def mkdir_if_not_exist(path):
    if not os.path.exists(path):
        try:
            os.makedirs(path)
        except OSError as e:
            print("Cannot create directory: " + path)
            exit()
    # else:
    #     print("File already exists")

# f = open("./newdir/file.txt", "w")
# f.write("test")
# f.close()
main()
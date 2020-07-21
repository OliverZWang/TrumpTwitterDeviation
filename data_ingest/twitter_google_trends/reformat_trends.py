from ConsolidateCSV import ConsolidateCSV

def main():

    twitter_path = "./trends_data/twitter/"
    twitter_start_date = "2016-01-10"
    ebd_date = "2020-06-28"
    twitter_consolidated = ConsolidateCSV(twitter_path, "twitter", twitter_start_date, ebd_date)

    google_path = "./trends_data/google/"
    google_start_date = "2017-03-18"
    google_consolidate = ConsolidateCSV(google_path, "google", google_start_date, ebd_date)

main()
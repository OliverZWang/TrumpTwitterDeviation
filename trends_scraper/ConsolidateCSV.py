import pandas as pd
from datetime import datetime
import os

class ConsolidateCSV():
    def __init__(self, path, data_source, start_date, end_date):
        self.path = path
        self.data_source = data_source
        self.date_range = pd.date_range(start=start_date, end=end_date)
        self.consolidate()

    def consolidate(self):
        self.f = open(self.data_source+"_consolidated.csv", "w")
        for date in self.date_range:
            date = extract_date(date)
            file_path = self.path + date + "-"+self.data_source+".csv"
            if os.path.exists(file_path):
                this_file = open(file_path, "r")
                text = this_file.read()
                text = date + "," + text + "\n"
                self.f.write(text)
                this_file.close()
            else:
                print("file "+file_path+" does not exist!")

        self.f.close()



# this function takes a pd datestamp and return the date part as a string
def extract_date(pd_date):
    list = str(pd_date).split()
    return list[0]
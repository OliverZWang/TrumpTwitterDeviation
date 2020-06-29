import requests
from bs4 import BeautifulSoup

class TrendPageScraper():
    def __init__(self, url, dest_twitter, dest_google):
        self.url = url
        self.dest_twitter = dest_twitter
        self.dest_google = dest_google
        self.twitter_trends = ''
        self.google_trends = ''
        self.get_trends()
        self.write_trends()

    def get_trends(self):
        self.page = requests.get(self.url)
        self.soup = BeautifulSoup(self.page.text, 'html.parser')

        iterator = 0
        all_ol = self.soup.find_all('ol') 
        ol_len = len(all_ol)

        while iterator < ol_len:
            ol_tag = all_ol[iterator]
            for item in ol_tag:
                if item != '\n':
                    # print(item.get_text())
                    # print('-'*20)
                    if iterator < 2:
                        self.twitter_trends += item.get_text()+','
                    else:
                        self.google_trends += item.get_text()+','
            iterator += 1
        self.twitter_trends = self.twitter_trends[:-1]
        self.google_trends = self.google_trends[:-1]
    
    def write_trends(self):
        self.f_t = open(self.dest_twitter, "w")
        self.f_t.write(self.twitter_trends)
        self.f_t.close()

        self.f_g = open(self.dest_google, "w")
        self.f_g.write(self.google_trends) 
        self.f_g.close()
        
        # print(self.f.read())

        

if __name__ == '__main__':
    #testing
    scraper = TrendPageScraper('https://us.trend-calendar.com/trend/2020-06-28.html', "./2020-06-28-twitter.csv", "./2020-06-28-google.csv")
    # print(scraper.twitter_trends)
    # print(scraper.google_trends)





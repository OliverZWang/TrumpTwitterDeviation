
def main():
	f = open("./stopwords.txt", "r")
	text = f.read().replace("\n", ",")
	print(text)
main()



# this program removes all empty files in a given directory
# it takes the path to the directory as an argument

import os
import sys

def main(path_to_directory):

    for filename in os.listdir(path_to_directory):

        path_to_file = os.path.join(path_to_directory, filename)

        if not is_non_zero_file(path_to_file):
            os.remove(path_to_file)

def is_non_zero_file(fpath):  
    return os.path.isfile(fpath) and os.path.getsize(fpath) > 0


main(sys.argv[1])


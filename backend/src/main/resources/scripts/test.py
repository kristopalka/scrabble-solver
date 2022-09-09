import sys
import json


name = sys.argv[1]

output = json.dumps({
    "output": {
        "hello": name
    }})

print(output)

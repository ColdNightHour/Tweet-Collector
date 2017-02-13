#!/usr/bin/env python

#	Copyright 2013 AlchemyAPI
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.


from __future__ import print_function
from alchemyapi import AlchemyAPI
import json


# Create the AlchemyAPI Object
alchemyapi = AlchemyAPI()

#Print Formatting
print('')
print('')
print('')
print('############################################')
print('#           Check Out Deez Tweetz          #')
print('############################################')
print('')
print('')

#Open File
f = open('tweets.txt')

#Read First Line
line = f.readline()

#Just as long as it is not the end of the file
while line:
	#Sends line to AlchemyAPI
	response = alchemyapi.sentiment('text', line)
	
	#If API responds with OK JSON status,
		#Print tweet, response, and if not neutral, print score
		
	if response['status'] == 'OK':
		print(line, 'type: ', response['docSentiment']['type'])
		if 'score' in response['docSentiment']:
			print('score: ', response['docSentiment']['score'])
	
	#Formatting
	print('')
	
	#Read next line
	line = f.readline()
    


#File close
f.close()

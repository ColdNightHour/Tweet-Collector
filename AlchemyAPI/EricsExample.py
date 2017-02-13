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

print('')
print('')
print('')
print('############################################')
print('#   Sentiment Analysis Example             #')
print('############################################')
print('')
print('')

f = open('tweets.txt')
line = f.readline()

while line:
	response = alchemyapi.sentiment('text', line)
	
	if response['status'] == 'OK':
		print(line, 'type: ', response['docSentiment']['type'])
		if 'score' in response['docSentiment']:
			print('score: ', response['docSentiment']['score'])
			
	print('')
	line = f.readline()

f.close()

	#response = alchemyapi.sentiment('text', demo_text)

	#if response['status'] == 'OK':
		#print('')
		#print('## Tweet Sentiment ##')
		#print('type: ', response['docSentiment']['type'])

		#if 'score' in response['docSentiment']:
			#print('score: ', response['docSentiment']['score'])
			
		#print('')
	#else:
		#print('Error in sentiment analysis call: ', response['statusInfo'])

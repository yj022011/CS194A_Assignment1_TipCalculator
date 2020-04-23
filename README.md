# CS194A_Assignment1

# Tip Calculator 

## *Yiting Ji*

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip. It also allows user to choose number of people splitting the bill and calculates the per-person bill.

Time spent: **2** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [x] User can enter in a bill amount (total amount to tip on)
* [x] User can enter a tip percentage (what % the user wants to tip).
* [x] The tip and total amount are updated immediately when any of the inputs changes.
* [x] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [x] Custom colors palette selected
* [x] Add emojis in the text describing the tip (“poor”, “good”, etc.)
* [x] Add the ability to split the bill across 1 to 16 people.
* [x] Add the ability to round the total bill up or down, which updates the tip amount per person amount automatically.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

![](tippy.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Adding the split option creates some bug, but was easily resolved.
Initial states of the tip amount, total amount, and per person amount must be addressed when base is empty. Otherwise the app will crash when base becomes empty. 

## License

    Copyright [2020] [Yiting Ji]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

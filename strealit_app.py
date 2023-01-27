import streamlit
import pandas
import requests

streamlit.markdown("<h1 style='text-align: center; color: Blue'>My Parent New Healthy Diner</h1>", unsafe_allow_html=True)
#streamlit.title ('My Parent New Healthy Diner')
streamlit.markdown("<h2 style='text-align: center; color: Blue'>'🍽 Breakefast Menu 🍽'</h2>", unsafe_allow_html=True)
#streamlit.header ('🍽 Breakefast Menu 🍽')
streamlit.markdown("<p style='text-align: center; color: Blue'>'🥔🥞 Allu Paratha 🥔🥞'</p>", unsafe_allow_html=True)
#streamlit.text ('🥔🥞 Allu Paratha 🥔🥞')
streamlit.markdown("<p style='text-align: center; color: Blue'>'🥚🥞 Anda Paratha 🥚🥞'</p>", unsafe_allow_html=True)
#streamlit.text ('🥚🥞 Anda Paratha 🥚🥞')
streamlit.markdown("<p style='text-align: center; color: Blue'>'🥞 Lebanese Bread 🥞'</p>", unsafe_allow_html=True)
#streamlit.text ('🍖 Qeema Paratha 🍖')
streamlit.markdown("<p style='text-align: center; color: Blue'>'🥛☕ Doodh Pati 🥛☕'</p>", unsafe_allow_html=True)
#streamlit.text ('🥛☕ Doodh Pati 🥛☕')
streamlit.markdown("<h2 style='text-align: center; color: Purple'>🍌🥭 Build Your Own Fruit Smoothie 🥝🍇</h2>", unsafe_allow_html=True)
#streamlit.header('🍌🥭 Build Your Own Fruit Smoothie 🥝🍇')

my_fruit_list = pandas.read_csv("https://uni-lab-files.s3.us-west-2.amazonaws.com/dabw/fruit_macros.txt")
my_fruit_list = my_fruit_list.set_index ('Fruit')
# Let's put a pick list here so they can pick the fruit they want to include 


# Let's put a pick list here so they can pick the fruit they want to include
streamlit.markdown("<h3 style='text-align: left; color: Purple'>Pick some fruits 🍉🍍🍌🥭🥝:</h2>", unsafe_allow_html=True)
fruits_selected = streamlit.multiselect(' ',list(my_fruit_list.index), ['Avocado','Strawberries'])
fruits_to_show = my_fruit_list.loc[fruits_selected]
#display the table on the page streamlit.dataframe(fruits_to_show)
streamlit.dataframe(fruits_to_show)

fruityvice_response = requests.get("https://fruityvice.com/api/fruit/watermelon")
streamlit.header("Fruityvice Fruit Advice!")
# write your own comment -what does the next line do? 
fruityvice_normalized = pandas.json_normalize(fruityvice_response.json())
# write your own comment - what does this do?
streamlit.dataframe(fruityvice_normalized)
fruit_choice = streamlit.text_input('What fruit would you like information about?','Kiwi')
streamlit.write('The user entered ', fruit_choice)

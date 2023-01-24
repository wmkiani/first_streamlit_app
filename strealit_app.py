import streamlit
import pandas

streamlit.title ('My Parent New Healthy Diner')
streamlit.header ('🍽 Breakefast Menu 🍽')
streamlit.text ('🥔🥞 Allu Paratha 🥔🥞')
streamlit.text ('🥚🥞 Anda Paratha 🥚🥞')
streamlit.text ('🍖 Qeema Paratha 🍖')
streamlit.text ('🥛☕ Doodh Pati 🥛☕')

streamlit.header('🍌🥭 Build Your Own Fruit Smoothie 🥝🍇')
my_fruit_list = pandas.read_csv("https://uni-lab-files.s3.us-west-2.amazonaws.com/dabw/fruit_macros.txt")
my_fruit_list = my_fruit_list.set_index ('Fruit')
# Let's put a pick list here so they can pick the fruit they want to include 
#streamlit.multiselect("Pick some fruits:", list(my_fruit_list.index))


#Pick Some Fruit
fruit_selected= streamlit.multiselect("Pick some fruits:", list(my_fruit_list.index),['Avocado','Strawberries'])
show_fruit=my_fruit_list.loc[fruit_selected]
streamlit.dataframe(show_fruit)

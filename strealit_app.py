import streamlit
import pandas

streamlit.markdown("<h1 style='text-align: center; color: red;'>Some title</h1>", unsafe_allow_html=True)
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

# Let's put a pick list here so they can pick the fruit they want to include
fruits_selected = streamlit.multiselect("pick some fruits.", list(my_fruit_list.index), ['Avocado','Strawberries'])
fruits_to_show = my_fruit_list.loc[fruits_selected]
#display the table on the page streamlit.dataframe(fruits_to_show)
streamlit.dataframe(fruits_to_show)

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
streamlit.dataframe(my_fruit_list)

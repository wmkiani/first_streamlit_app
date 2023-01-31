import streamlit
import pandas
import requests
import snowflake.connector
from urllib.error import URLError

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

streamlit.header("Fruityvice Fruit Advice!")
try:
  fruit_choice = streamlit.text_input('What fruit would you like information about?')
  if not fruit_choice:
      streamlit.error("Please select a fruit to get information.")
  else:
      fruityvice_response = requests.get("https://fruityvice.com/api/fruit/"+fruit_choice)
      fruityvice_normalized = pandas.json_normalize(fruityvice_response.json())
      streamlit.dataframe(fruityvice_normalized)
except URLError as e:
    streamlit.error()

#Snowflake-related functions
def get_fruit_load_list():
    with my_cnx.cursor() as my_cur:
         my_cur.execute("SELECT * from fruit_load_list")
         return my_cur.fetchall()
# Add a button to load the fruit
if streamlit.button('Get fruit load List '):
   my_cnx = snowflake.connector.connect(**streamlit.secrets["snowflake"])
   my_data_rows = get_fruit_load_list()
   streamlit.dataframe(my_data_rows)


#streamlit.write('Thanks for adding :', add_my_fruit)


#Allow end user to add a fruit to the list

def insert_row_snowflake(new_fruit):
    with my_cnx.cursor() as my_cur:
         my_cur.execute("insert into fruit_load_list values ('"+new_fruit+"')")
         return "Thanks for adding" + new_fruit
add_my_fruit = streamlit.text_input('What fruit would you like add?')        

if streamlit.button('Add fruit to the List '):
   my_cnx = snowflake.connector.connect(**streamlit.secrets["snowflake"])
   back_from_function = insert_row_snowflake(add_my_fruit)
   streamlit.text(back_from_function)
        
        
        
        
        

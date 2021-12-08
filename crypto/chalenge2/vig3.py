# https://github.com/minoguep/vigenere_cipher_code/blob/main/vigenere_cipher_tutorial.ipynb
import string
available_characters = list(string.ascii_uppercase) + [str(i) for i in range(10)] + list(string.punctuation) + [" "]

# two dictionaries we will use to map characters to itegers and vice versa
base_char_int_mapping = {}
base_int_char_mapping = {}
i = 0
# create a dictionary of characters to integer mappings, we will use ths to make the vinegere square
for character in available_characters:        
    base_char_int_mapping[character] = i
    base_int_char_mapping[i] = character
    i+=1

# determine the max value so we know when we need to roll back to 0
max_char_value = i-1

# loop through each character and encode the character by adding an 
# offset to it for each letter in the available characters
offset = 0
encoder = {}
decoder = {}
for key_char in available_characters:
    key_encoder_lookup = {}
    key_decoder_lookup = {}
    for plain_text_char in available_characters:
        offset_char_int_value = base_char_int_mapping[plain_text_char] + offset
        if offset_char_int_value > max_char_value:
            offset_char_int_value = offset_char_int_value - max_char_value - 1
        offset_character_mapping = base_int_char_mapping[offset_char_int_value]
        key_encoder_lookup[plain_text_char] = offset_character_mapping
        key_decoder_lookup[offset_character_mapping] = plain_text_char
    encoder[key_char] = key_encoder_lookup
    decoder[key_char] = key_decoder_lookup
    offset +=1

print(encoder['B']['V'])  

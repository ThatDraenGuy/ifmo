import time

from pynput.keyboard import Controller, Key

"""
Вводит комманды из файла за вас с клавиатуры, переводя их в бинарный формат.
В конце устанавливает IP на начало исполнения программы.  

Для установки библиотеки ввода:
pip install pynput

Структура файла commands_my.txt:
2 числа в первой строке файла - адрес начала программы, адрес начала исполнения. 
каждая комманда на новой строке 

03D5 03FF
0200
4000
0100
"""
FILE_PATH = "lab4/cmd2.txt"

keyboard = Controller()
KEYS = {"enter_addr": Key.f4, "write": Key.f5}

def read_file(filename):
  with open(filename, "r") as f:
    return f.readlines()


def type_command(string):
  for character in string:
    keyboard.type(character)
    time.sleep(0.02)
  keyboard.tap(KEYS["write"])


def enter_addr(start_addr):
  keyboard.type(start_addr)
  time.sleep(0.03)
  keyboard.tap(KEYS["enter_addr"])


def hex_to_bits(hex_num):
  return bin(int(hex_num, 16))[2:].zfill(16)


def main():

  print("Program will start in 4 seconds! Move your cursor on a bcomp app window.")
  time.sleep(4)
  lines = read_file(FILE_PATH)
  prog_start_addr, exec_start_addr = map(hex_to_bits, lines.pop(0).split())
  enter_addr(prog_start_addr)

  for command in lines:
    bits_command = hex_to_bits(command)
    type_command(bits_command)
    time.sleep(0.1)

  enter_addr(exec_start_addr)


if __name__ == "__main__":
  main()

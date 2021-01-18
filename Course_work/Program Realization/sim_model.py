from model import *
from process import *
import pandas as pd


def sim_model():
    times = 0
    while times < 16:
        # lowest general time
        # highest general time
        # lowest time of left processing
        # closest directive term

        c1 = Create([100.0, 200.0], 120.0, 110.0, 90.0, [20.0, 60.0])

        p1 = Process(task_priority="closest directive term")
        p2 = Process(task_priority="lowest time of left processing")
        p3 = Process(task_priority="closest directive term")

        c1.name = 'INCOME TASK'
        p1.name = 'PROGRAMMING PROCESS'
        p2.name = 'WRITE ON HARDWARE'
        p3.name = 'TESTING'

        c1.next_element = [p1]
        p1.next_element = [p2]
        p2.next_element = [p3]

        element_list = [c1, p1, p2, p3]
        model = Model(element_list)
        model.simulate(1200.0, flag_info=False)

        Element.next_id = 0
        times += 1

    print(Model.Result_table)

    Model.Result_table.to_excel(r'C:\Users\nturk\Desktop\cw_exp\end_3.xlsx', sheet_name='1', index=False)


sim_model()

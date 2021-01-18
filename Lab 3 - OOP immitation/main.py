from model import *
from process import *

pd.set_option('display.max_columns', None)
pd.set_option('max_colwidth', None)
pd.set_option('max_rows', None)
pd.set_option('display.width', 1000)

col_name = ['Create delay', 'SMO_1 delay', 'SMO_1 channels', 'Max_Queue', 'SMO_2 delay', 'SMO_2 channels', 'Max_Queue',
            'SMO_3 delay', 'SMO_3 channels', 'Max_Queue',
            'SMO_4 delay', 'SMO_4 channels', 'Max_Queue',
            'Created_quantity', 'Failure_1', 'Failure_2', 'Failure_3', 'Failure_4', 'Failure probability',
            'Mean queue_1', 'Mean load_1', 'Mean queue_2', 'Mean load_2',
            'Mean queue_3', 'Mean load_3', 'Mean queue_4', 'Mean load_4']

if __name__ == '__main__':

    c1_1 = Create(2.0)
    p1_1 = Process(2.0, 5)
    p2_1 = Process(2.0, 5)
    p3_1 = Process(2.0, 5)
    p4_1 = Process(2.0, 5)
    p5_1 = Process(0)

    c1_1.next_element = [p1_1]
    p1_1.next_element = [p2_1, p3_1]
    p2_1.next_element = [p5_1]
    p3_1.next_element = [p4_1]
    p4_1.next_element = [p1_1, p5_1]

    p1_1.probability = ([0.5, 0.5])
    p4_1.probability = ([0.5, 0.5])

    p1_1.max_queue = 3
    p2_1.max_queue = 3
    p3_1.max_queue = 3
    p4_1.max_queue = 3

    c1_1.distribution = 'expo'
    p1_1.distribution = 'expo'
    p2_1.distribution = 'expo'
    p3_1.distribution = 'expo'
    p4_1.distribution = 'expo'
    p5_1.distribution = 'expo'

    Element.next_id = 0

    c1 = Create(0.5)
    p1 = Process(2.0, 10)
    p2 = Process(2.0)
    p3 = Process(2.0)
    p4 = Process(2.0)
    p5 = Process(0)

    c1.next_element = [p1]
    p1.next_element = [p2, p3]
    p2.next_element = [p5]
    p3.next_element = [p4]
    p4.next_element = [p1, p5]

    p1.probability = ([0.5, 0.5])
    p4.probability = ([0.5, 0.5])

    p1.max_queue = 3
    p2.max_queue = 3
    p3.max_queue = 3
    p4.max_queue = 3

    c1.name = 'CREATOR'
    p1.name = 'PROCESSOR1'
    p2.name = 'PROCESSOR2'
    p3.name = 'PROCESSOR3'
    p4.name = 'PROCESSOR4'
    p5.name = 'DISPOSED'

    c1.distribution = 'expo'
    p1.distribution = 'expo'
    p2.distribution = 'expo'
    p3.distribution = 'expo'
    p4.distribution = 'expo'
    p5.distribution = 'expo'

    element_list = [c1, p1, p2, p3, p4, p5]
    element_list_1 = [c1_1, p1_1, p2_1, p3_1, p4_1, p5_1]
    model = Model(element_list_1)
    model.simulate(1000.0, False)
    model = Model(element_list)
    model.simulate(1000.0, True)


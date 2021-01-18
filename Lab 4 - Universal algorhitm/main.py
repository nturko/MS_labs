from model_bank import *
from process_bank import *
from create_bank import *
from model_hospital import *
from process_hospital import *
from create_hospital import *

if __name__ == '__main__':

    c1 = CreateBank(0.5)
    p1 = ProcessBank(0.3)
    p2 = ProcessBank(0.3)

    c1.next_element = [p1, p2]

    c1.probability = [0.1, 0.9]

    p1.max_queue = 3
    p2.max_queue = 3

    c1.name = 'NEW CAR'
    p1.name = 'CASHIER_1'
    p2.name = 'CASHIER_2'

    c1.distribution = 'expo'
    p1.distribution = 'expo'
    p2.distribution = 'expo'

    element_list = [c1, p1, p2]
    bank = ModelBank(element_list)
    bank.simulate(500.0, True)
    '''

    c1 = CreateHospital([5.0, 5.0, 5.0])
    p1 = ProcessHospital([15.0, 40.0, 30.0], channels=2)
    p2 = ProcessHospital([3.0, None, None], channels=3, delay_dev=8.0)
    p3 = ProcessHospital([None, 4.5, 4.5], k=3)
    p4 = ProcessHospital([None, 4.0, 4.0], channels=2, k=2,  change_type='able')
    p5 = ProcessHospital([0.0, None, None])
    p6 = ProcessHospital([None, None, 0.0])

    c1.create_probability = [0.5, 0.1, 0.4]

    c1.name = 'NEW PATIENT'
    p1.name = 'REGISTER PATIENT'
    p2.name = 'PATIENT GOES TO WARD WITH ATTENDANT'
    p3.name = 'PATIENT REGISTER FOR LABORATORY TEST'
    p4.name = 'PATIENT BEEN MADE TEST'
    p5.name = 'PATIENTS IN WARDS'
    p6.name = 'PATIENT HAS LEFT THE HOSPITAL'

    c1.next_element = [p1, p1, p1]
    p1.next_element = [p2, p3, p3]
    p2.next_element = [p5, None, None]
    p3.next_element = [None, p4, p4]
    p4.next_element = [None, p1, p6]

    c1.distribution = 'expo'
    p1.distribution = 'expo'
    p2.distribution = 'uniform'
    p3.distribution = 'erlang'
    p4.distribution = 'erlang'
    p5.distribution = 'expo'
    p6.distribution = 'expo'
    
    element_list = [c1, p1, p2, p3, p4, p5, p6]
    hospital = ModelHospital(element_list)
    hospital.simulate(1000.0, True)
    '''

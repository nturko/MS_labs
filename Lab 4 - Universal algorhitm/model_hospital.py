from process_hospital import *
from create_hospital import *


class ModelHospital(object):

    def __init__(self, elements_list):
        self.element_list = elements_list
        self.t_next = 0
        self.event = 0
        self.t_curr = 0
        self.queue_move = 0
        self.time = 0
        self.all_clients, self.out_clients = 0, 0

    def simulate(self, time, flag):
        while self.t_curr < time:
            self.t_next = np.inf
            for element in self.element_list:
                t_next_val = np.min(element.t_next)
                if t_next_val < self.t_next:
                    self.t_next = t_next_val
                    self.event = element.id_element
            if flag is True:
                print(f"\nIt's time for {self.element_list[self.event].name}, time= {self.t_next}")
            for element in self.element_list:
                element.statistics(self.t_next - self.t_curr)

            self.t_curr = self.t_next
            for element in self.element_list:
                element.t_curr = self.t_curr

            self.element_list[self.event].out_act()
            for element in self.element_list:
                if self.t_curr in element.t_next:
                    element.out_act()
            self.print_info(flag)
        self.print_result(flag)

    def print_info(self, flag):
        if flag is True:
            for element in self.element_list:
                element.print_info()

    def print_result(self, flag):
        if flag is True:
            print("\n---- RESULTS ---")
            p1, p3, p4, p_all_queue = 0, 0, 0, 0
            for element in self.element_list:
                element.print_res()
                if isinstance(element, CreateHospital):
                    print(f"Failures because of block: {element.block_failure}")
                if isinstance(element, ProcessHospital):
                    p = element
                    if p.id_element == 1:
                        p1 += p.quantity
                    elif p.id_element == 4:
                        p4 += p.quantity
                    elif p.id_element == 6:
                        p3 += p.quantity
                    p_all_queue += sum(p.queue)
                    print(f"Last observed queue={sum(p.queue)}  |  Max queue={p.max_queue} "
                          f"\nMean load={p.mean_load / self.t_curr} | "
                          f"Mean length of queue={p.mean_queue/self.t_curr}"
                          f"'\nAverage time patient waiting time :{p.mean_queue/p.quantity}")

            print(f'\nAverage interval of time laboratory done tests:{p4/self.t_curr}')



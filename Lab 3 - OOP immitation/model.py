from process import *
from create import *
import pandas as pd
col_name = ['Create delay', 'SMO_1 delay', 'SMO_1 channels', 'Max_Queue', 'SMO_2 delay', 'SMO_2 channels', 'Max_Queue',
            'SMO_3 delay', 'SMO_3 channels', 'Max_Queue',
            'SMO_4 delay', 'SMO_4 channels', 'Max_Queue',
            'Created_quantity', 'Failure_1', 'Failure_2', 'Failure_3', 'Failure_4', 'Failure probability',
            'Mean queue_1', 'Mean load_1', 'Mean queue_2', 'Mean load_2',
            'Mean queue_3', 'Mean load_3', 'Mean queue_4', 'Mean load_4']


class Model(object):
    verification_table = pd.DataFrame(columns=col_name)
    create_delay, quantity_0 = 0, 0
    p1_delay, p2_delay, p3_delay, p4_delay = 0, 0, 0, 0
    p1_load, p2_load, p3_load, p4_load = 0, 0, 0, 0
    p1_queue, p2_queue, p3_queue, p4_queue = 0, 0, 0, 0
    p1_queue_avg, p2_queue_avg, p3_queue_avg, p4_queue_avg = 0, 0, 0, 0
    p1_channel, p2_channel, p3_channel, p4_channel = 0, 0, 0, 0
    p1_failure, p2_failure, p3_failure, p4_failure = 0, 0, 0, 0

    def __init__(self, elements_list):
        self.element_list = elements_list
        self.t_next = 0
        self.event = 0
        self.t_curr = 0

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
        self.verification(flag)

    def print_info(self, flag):
        if flag is True:
            for element in self.element_list:
                element.print_info()

    def print_result(self, flag):
        if flag is True:
            print("\n---- RESULTS ---")
            for element in self.element_list:
                element.print_res()
                if isinstance(element, Process):
                    p = element
                    print(f"Mean length of queue={p.mean_queue/self.t_curr}  |  Max queue={p.max_queue}"
                          f" \nFailure probability={p.failure / (p.quantity + p.failure)}  |  "
                          f"Mean load={p.mean_load / self.t_next}")

    def verification(self, flag):
        for element in self.element_list:
            if isinstance(element, Create):
                c = element
                Model.quantity_0 = c.quantity
                Model.create_delay = c.delay_mean
            elif isinstance(element, Process):
                p = element
                if p.id_element == 1:
                    Model.p1_delay = p.delay_mean
                    Model.p1_queue = p.max_queue
                    Model.p1_queue_avg = p.mean_queue / self.t_curr
                    Model.p1_channel = p.channel
                    Model.p1_failure = p.failure
                    Model.p1_load = p.mean_load / self.t_curr
                elif p.id_element == 2:
                    Model.p2_delay = p.delay_mean
                    Model.p2_queue = p.max_queue
                    Model.p2_queue_avg = p.mean_queue / self.t_curr
                    Model.p2_channel = p.channel
                    Model.p2_failure = p.failure
                    Model.p2_load = p.mean_load / self.t_curr
                elif p.id_element == 3:
                    Model.p3_delay = p.delay_mean
                    Model.p3_queue = p.max_queue
                    Model.p3_queue_avg = p.mean_queue / self.t_curr
                    Model.p3_channel = p.channel
                    Model.p3_failure = p.failure
                    Model.p3_load = p.mean_load / self.t_curr
                elif p.id_element == 4:
                    Model.p4_delay = p.delay_mean
                    Model.p4_queue = p.max_queue
                    Model.p4_queue_avg = p.mean_queue / self.t_curr
                    Model.p4_channel = p.channel
                    Model.p4_failure = p.failure
                    Model.p4_load = p.mean_load / self.t_curr
                elif p.id_element == 5:
                    print(" ")
        p_failure = (Model.p1_failure + Model.p2_failure + Model.p3_failure + Model.p4_failure) / Model.quantity_0

        verification = pd.DataFrame([[Model.create_delay, Model.p1_delay, Model.p1_channel, Model.p1_queue,
                                      Model.p2_delay, Model.p2_channel, Model.p2_queue,
                                      Model.p3_delay, Model.p3_channel, Model.p3_queue, Model.p4_delay,
                                      Model.p4_channel, Model.p4_queue,
                                      Model.quantity_0, Model.p1_failure, Model.p2_failure, Model.p3_failure,
                                      Model.p4_failure, p_failure,
                                      Model.p1_queue_avg, Model.p1_load, Model.p2_queue_avg, Model.p2_load, Model.p3_queue_avg,
                                      Model.p3_load, Model.p4_queue_avg, Model.p4_load]], columns=col_name)

        Model.verification_table = Model.verification_table.append(verification, ignore_index=True)
        if flag is True:
            print(Model.verification_table)

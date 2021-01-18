from element import *
import numpy as np


class CreateHospital(Element):
    def __init__(self, delay, delay_dev=None):
        super().__init__(name_element=None, delay=delay, delay_dev=delay_dev)
        self.block_failure = 0
        self.create_probability = []
        self.type_task = np.random.choice(a=[1, 2, 3], p=[0.5, 0.1, 0.4])

    def out_act(self):
        super().out_act()
        self.t_next[0] = self.t_curr + super().get_delay(self.type_task-1)
        max_unavailable_devices, unavailable_devices = self.get_unavailable_status(self.type_task)
        if unavailable_devices < max_unavailable_devices:
            self.able_to_move(self.type_task)
            self.type_task = np.random.choice(a=[1, 2, 3], p=self.create_probability)
        elif unavailable_devices == max_unavailable_devices:
            for element in self.next_element:
                if sum(element.queue) == element.max_queue:
                    if len(self.next_element) > 1:
                        element.failure += 1
                        self.type_task = np.random.choice(a=[1, 2, 3], p=self.create_probability)
                    else:
                        self.block_failure += 1
                elif sum(element.queue) < element.max_queue:
                    element.in_act(self.type_task)
                    self.type_task = np.random.choice(a=[1, 2, 3], p=self.create_probability)
                    break
                else:
                    continue
                break
            '''
        if len(self.priority) > 0:
            if max(next_queue) == 0:
                self.priority[0].in_act()
            elif len(self.probability) > 1:
                next_el = np.random.choice(a=self.next_element, p=self.probability)
                next_el.in_act()
            elif len(self.probability) == 1:
                way_id = next_queue.index(min(next_queue))
                self.next_element[way_id].in_act()
        elif len(self.priority) == 0 and len(self.probability) >= 1:
            next_el = np.random.choice(a=self.next_element, p=self.probability)
            next_el.in_act()
            '''

    @property
    def priority(self):
        return self.__priority

    @priority.setter
    def priority(self, value):
        self.__priority = value

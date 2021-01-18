from element import *
import numpy as np


class CreateBank(Element):
    def __init__(self, delay):
        super().__init__(name_element=None, delay=delay)
        self.priority = []

    def out_act(self):
        super().out_act()
        self.t_next[0] = self.t_curr + super().get_delay()
        next_queue = list()
        for i in range(len(self.next_element)):
            next_queue.append(self.next_element[i].queue)
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

    @property
    def priority(self):
        return self.__priority

    @priority.setter
    def priority(self, value):
        self.__priority = value

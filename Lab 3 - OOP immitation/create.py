from element import *


class Create(Element):
    def __init__(self, delay):
        super().__init__(name_element=None, delay=delay)

    def out_act(self):
        super().out_act()
        self.t_next[0] = self.t_curr + super().get_delay()
        self.next_element[0].in_act()

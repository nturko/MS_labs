import numpy as np


class Position(object):
    pos_id = 1

    def __init__(self, name_pos=None):
        self.id = Position.pos_id
        Position.pos_id += 1
        self.name = name_pos
        if name_pos is None:
            self.name = self.id
        self.transition_move = []
        self.arcs_condition = [0]
        self.marker = 0
        self.multiplicity = [1]*len(self.transition_move)
        self.max_marker, self.min_marker, self.average_markers = 0, 0, 0

    def check_conflict(self):
        conflict_marker = self.marker
        arc_number = len(self.transition_move)
        transitions_ready_to_start = []
        if arc_number > 1:
            for transition in self.transition_move:
                transitions_ready_to_start.append(transition.check_prev_positions())
            transitions_ready = sum(transitions_ready_to_start)
            if transitions_ready > 1:
                for i in range(len(self.arcs_condition)):
                    self.arcs_condition[i] = 0
                transitions_ready_index = []
                for i in range(len(transitions_ready_to_start)):
                    if transitions_ready_to_start[i] == 1:
                        transitions_ready_index.append(i)
                random_arc = np.random.choice(transitions_ready_index)
                for i in range(len(transitions_ready_index)):
                    if transitions_ready_index[i] == random_arc:
                        self.arcs_condition[random_arc] = 1
                        conflict_marker -= self.multiplicity[random_arc]
                    else:
                        if conflict_marker - self.multiplicity[random_arc] >= self.multiplicity[transitions_ready_index[i]]:
                            conflict_marker -= self.multiplicity[transitions_ready_index[i]]
                            self.arcs_condition[transitions_ready_index[i]] = 1

    def arc_condition(self):
        if len(self.multiplicity) == 0:
            self.multiplicity = [1] * len(self.transition_move)
        self.arcs_condition = [0] * len(self.transition_move)
        if len(self.multiplicity) > 0:
            for i in range(len(self.multiplicity)):
                if self.marker >= self.multiplicity[i]:
                    self.arcs_condition[i] = 1

    def statistics(self, time_end, time_start):
        if self.max_marker < self.marker:
            self.max_marker = self.marker
        elif self.min_marker > self.marker:
            self.min_marker = self.marker
        self.average_markers += self.marker * (time_end - time_start)

    def print_info(self):
        print(f'\nP: {self.name} | Current marker:{self.marker}' 
              f'\nPosition arcs multiplicity: {self.multiplicity}')

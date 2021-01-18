
class Transition(object):
    transition_id = 1

    def __init__(self, name_pos=None):
        self.move_to_positions = []
        self.previous_positions = []
        self.multiplicity = [1]*len(self.move_to_positions)
        self.id = Transition.transition_id
        self.name = name_pos
        if name_pos is None:
            self.name = self.id
        Transition.transition_id += 1
        self.action, self.int_action = 0, 0

    def check_prev_positions(self):
        condition_for_start = len(self.previous_positions)
        current_condition = 0
        for position in self.previous_positions:
            transition_index = position.transition_move.index(self)
            if position.arcs_condition[transition_index] >= 1:
                current_condition += 1
        if current_condition >= condition_for_start:
            return 1
        else:
            return 0

    def transition_start(self):
        transition_condition = len(self.previous_positions)
        current_condition = 0
        for prev_pos in self.previous_positions:
            transition_index = prev_pos.transition_move.index(self)
            if prev_pos.arcs_condition[transition_index] >= 1:
                current_condition += 1

        if current_condition == transition_condition:
            print(f'\n--- T: {self.name} START ---')
            for prev_pos in self.previous_positions:
                transition_index = prev_pos.transition_move.index(self)
                prev_pos.arcs_condition[transition_index] -= 1
                prev_pos.marker -= prev_pos.multiplicity[transition_index]
                prev_pos.print_info()
            self.action = 1

    def transition_end(self):
        if self.action == 1:
            if len(self.multiplicity) == 0:
                self.multiplicity = [1] * len(self.move_to_positions)
            print(f'\n--- T: {self.name} END ---')
            for i in range(len(self.move_to_positions)):
                self.move_to_positions[i].marker += self.multiplicity[i]
                self.move_to_positions[i].print_info()
            self.action = 0
            self.int_action += 1

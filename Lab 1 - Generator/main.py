from generators import *

gen = Generator()

x1 = gen.generator_1(1/2, 10000)
y1 = gen.interval_table(x1, 20)
gen.histogram(y1)
x2 = gen.generator_2(1, 2, 10000)
y2 = gen.interval_table(x2, 20)
gen.histogram(y2)
x3 = gen.generator_3(10000)
y3 = gen.interval_table(x3, 20)
gen.histogram(y3)
gen.anylyze(x1, y1)
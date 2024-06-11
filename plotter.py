import matplotlib.pyplot as plt
from scipy.interpolate import make_interp_spline, BSpline
import numpy as np

x = []
y = []


with open('prims.txt') as f:
    for line in f.readlines():
        coords = line.split(' ')
        x += [int(coords[0])]
        y += [float(coords[1])]


spl = make_interp_spline(x, y)
 
# Returns evenly spaced numbers
# over a specified interval.
xnew = np.linspace(min(x), max(x), 10)
ynew = spl(xnew)
 
# Plotting the Graph
plt.scatter(x, y)
plt.plot(xnew, ynew)

plt.xlabel('No. of vertices')
plt.xticks(np.arange(0, max(x) + 100, 100))

plt.ylabel('Elapsed time (ms)')
plt.yticks(np.arange(0, max(y) + 10, 10))

plt.show()

# s = UnivariateSpline(x, y, s=20)
# xs = np.linspace(0, 29, 100)
# ys = s(xs)

# plt.plot(x, y, 'o')
# plt.plot(xs, ys)
# plt.show()
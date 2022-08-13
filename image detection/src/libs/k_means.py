import numpy as np
from scipy.spatial.distance import cdist


def kmeans(x, k, no_of_iterations=10):
    x = np.array(x)

    idx = np.random.choice(len(x), k, replace=False)
    centroids = x[idx, :]
    distances = cdist(x, centroids, 'euclidean')
    points = np.array([np.argmin(i) for i in distances])

    for _ in range(no_of_iterations):
        centroids = []
        for idx in range(k):
            temp_cent = x[points == idx].mean(axis=0)
            centroids.append(temp_cent)

        centroids = np.vstack(centroids)
        distances = cdist(x, centroids, 'euclidean')
        points = np.array([np.argmin(i) for i in distances])

    return round_all_points(centroids)


def round_all_points(arrays):
    out_array = []
    for array in arrays:
        nums = []
        for num in array:
            nums.append(round(num))
        out_array.append(nums)
    return out_array

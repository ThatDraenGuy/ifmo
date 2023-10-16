import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

class KNN:
    def __init__(self, k: int, col_names: list[str]):
        self.k = k
        self.col_names = col_names
        self.x = None
        self.y = None
    
    def _prepare_x(self, x):
        return np.array(x[self.col_names])
    
    def _prepare_y(self, y):
        return np.array(y)

    def train(self, x, y):
        self.x = self._prepare_x(x)
        self.y = self._prepare_y(y)

    def predict(self, x):
        if self.x is None or self.y is None:
            raise Exception('not yet trained')

        x = self._prepare_x(x)
        answer = []

        for row in x:
            tmp = np.linalg.norm(self.x - row, axis=1)
            k_ids = tmp.argsort()[:self.k]
            k_classes = self.y[k_ids]
            classes, counts = np.unique(k_classes, return_counts=True)
            winner = counts.argsort()[0]
            answer.append(classes[winner])

        return np.array(answer)
  

class Utils:    
    def split(data: pd.DataFrame, parameter: str):
        return (data.drop(parameter, axis=1), data[parameter])

    def normalize(train: pd.DataFrame, test: pd.DataFrame):
        for col in train.columns:
            min_x = train[col].min()
            max_x = train[col].max()
            train[col] = (train[col] - min_x) / (max_x - min_x)
            test[col] = (test[col] - min_x) / (max_x - min_x)

    def error_matrix(pred_y, true_y, n):
        res = np.zeros((n, n))

        for pred, true in zip(pred_y, true_y):
            res[int(pred), true] += 1

        return res

    def show_matrix(plot, pred_y, true_y, n):
        res = Utils.error_matrix(pred_y-1, true_y-1, n)
        plot.matshow(res)
        plot.set_xlabel('True class')
        plot.set_ylabel('Predicted class')

        for (i, j), z in np.ndenumerate(res):
            plot.text(j, i, str(int(z)), ha='center', va='center')
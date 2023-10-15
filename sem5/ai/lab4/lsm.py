import numpy as np
import pandas as pd

class LSM:
    def __init__(self, col_names: list[str]):
        self.col_names = col_names
        self.b = None
    
    def _prepare_x(self, x):
        x = np.array(x[self.col_names])
        return np.concatenate((np.ones((len(x), 1)), x), axis=1)
    
    def _prepare_y(self, y):
        return np.array(y)


    def train(self, x: pd.DataFrame, y: pd.Series):
        x = self._prepare_x(x)
        y = self._prepare_y(y)
        self.b = (np.linalg.inv((x.T @ x)) @ x.T @ y).T

    def predict(self, x: pd.DataFrame) -> list[float]:
        if self.b is None:
            raise Exception('not yet trained')
        return self._prepare_x(x) @ self.b

class Utils:
    def sum_of_squares(true_y, pred_y):
        return np.sum(np.square(true_y - pred_y))

    def determination_coefficient(true_y, pred_y):
        mean_y = np.mean(true_y)
        rss = np.sum(np.square(true_y - pred_y))
        tss = np.sum(np.square(true_y - mean_y))

        return 1 - rss / tss

    def split(data: pd.DataFrame, parameter: str):
        return (data.drop(parameter, axis=1), data[parameter])
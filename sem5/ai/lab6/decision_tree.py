import numpy as np
import pandas as pd
from math import log2


class Node:
    # def pred(self, x):
    #     pass

    def pred_prob(self, x):
        pass

    def print_node(self, indent=0):
        pass


class ComplexNode(Node):
    def __init__(
        self, 
        feature_name: str,
        value_to_node: dict[str, 'Node']
    ):
        self.feature_name = feature_name
        self.value_to_node = value_to_node

    # def pred(self, x):
    #     feature_value = x[self.feature_name]
    #     if feature_value not in self.value_to_node:
    #         return None
    #     return self.value_to_node[feature_value].pred(x)

    def pred_prob(self, x):
        feature_value = x[self.feature_name]
        if feature_value not in self.value_to_node:
            return 0
        return self.value_to_node[feature_value].pred_prob(x)

    def print_node(self, indent=0):
        for value, node in self.value_to_node.items():
            print('\t' * indent + f'{self.feature_name} == {value}:')
            node.print_node(indent+2)


class LeafNode(Node):
    def __init__(
        self,
        leaf_value: str,
        prob: float
    ):
        self.leaf_value = leaf_value
        self.prob = prob

    # def pred(self, x):
    #     return self.leaf_value

    def pred_prob(self, x):
        return (self.leaf_value, self.prob)

    def print_node(self, indent=0):
        print('\t' * indent + f'-> {self.leaf_value} ({self.prob})')


class DecisionTree:
    def __init__(self, cols: list[str]):
        self.cols = cols

    def fit(self, x, y):
        self.root = self._build_node(x[self.cols], y, DecisionTree._entropy(y))

    def pred(self, x):
        res = []
        for row in x[self.cols].to_records():
            res.append(self.root.pred_prob(row)[0])
        return res

    def pred_prob(self, x):
        res = []
        for row in x[self.cols].to_records():
            res.append(self.root.pred_prob(row))
        return res

    def _build_node(self, x, y, parent_info: float) -> Node:
        if len(y.unique()) == 1:
            return LeafNode(y.unique()[0], 1)

        best_gain = 0
        best_gain_info = 0
        best_gain_col = None
        
        for col in self.cols:
            vals_info = DecisionTree._cond_entropy(x, y, col)

            if parent_info - vals_info > best_gain:
                best_gain = parent_info - vals_info
                best_gain_info = vals_info
                best_gain_col = col

        if best_gain_col is None:
            mode = y.mode()[0]
            return LeafNode(mode, y.value_counts()[mode] / len(y))

        values_to_node = {}

        vals = x[best_gain_col].unique()
        for val in vals:
            values_to_node[val] = self._build_node(x[x[best_gain_col] == val], y[x[best_gain_col] == val], best_gain_info)

        return ComplexNode(best_gain_col, values_to_node)

    def print_tree(self):
        self.root.print_node()

    def _cond_entropy(x, y, col):
        vals = x[col].unique()
        vals_info = 0
        
        for val in vals:
            val_weight = x[col].value_counts()[val] / len(x)
            val_entropy = DecisionTree._entropy(y[x[col] == val])
            vals_info += val_weight * val_entropy
        return vals_info
        

    def _entropy(y) -> float:
        cls_names = y.unique()
        res = 0

        for cls in cls_names:
            cls_prob = y.value_counts()[cls] / len(y)
            res -= cls_prob * log2(cls_prob)
        
        return res


class Utils:
    def split(x, y, samples_amount: float, random_state: int):

        test_x = x.sample(frac=samples_amount, random_state=random_state)
        test_y = y[test_x.index]
        train_x = x.drop(test_x.index, axis=0)
        train_y = y[train_x.index]
        return (test_x, test_y, train_x, train_y)

    def confusion_matrix(pred_y, true_y):
        # TP FP
        # FN TN
        res = np.zeros((2, 2))

        for pred, true in zip(pred_y, true_y):
            pred = 1 if pred == 'e' else 0
            true = 1 if true == 'e' else 0
            res[pred][true] += 1
        
        return res

    def confusion_matrix_prob(pred_probs, true_y, threshold):
        res = np.zeros((2, 2))

        for pred_prob, true in zip(pred_probs, true_y):
            pred = 1 if pred_prob >= threshold else 0
            true = 1 if true == 'e' else 0
            res[pred][true] += 1

        return res

    def accuracy(conf):
        # (TP + TN) / (TP + FP + FN + TN)
        return (conf[1][1] + conf[0][0]) / sum(conf.flatten())

    def precision(conf):
        # TP / (TP + FP)
        return conf[1][1] / (conf[1][1] + conf[1][0])

    def recall(conf):
        # TP / (TP + FN)
        return conf[1][1] / (conf[1][1] + conf[0][1])

    def fallout(conf):
        # FP / (FP + TN)
        return conf[1][0] / (conf[1][0] + conf[0][0])
    
    def fill_mode(target: pd.DataFrame, column: str):
        target[column].fillna(target[column].mode()[0], inplace=True)

import csv
import numpy as np
import math
from sklearn.externals import joblib

with open('test/profession_train.csv', 'rb') as f:
    reader = csv.reader(f)
    F = []
    for row in reader:
        F.append(row)
    X = np.asarray(F)
    name = X[:, 0]
    prof = X[:, 1]
    y_score = X[:, 2]
    y_score = y_score.astype(np.int)
    feature = X[:, 4:]
    feature = feature.astype(np.int)

    label = []
    label1 = []
    m = len(name)
    acc = 0
    print "Logistic Regression"
    for i in range(m):
        filename = "Classifier/Profession/LogisticRegression/"+prof[i]+".pkl"
        try:
            clf_logistic = joblib.load(filename)
            l = clf_logistic.predict_proba(feature[i, :].reshape(1, -1))[0, 1]
            l1 = int(l * 8)
            l = math.pow(2, 7) * l + 1
            l = math.log(l, 2)
            l = int(l)
            label.append(l)
            label1.append(l1)
        except IOError:
            print "Cannot Find"
            label.append(0)
            label1.append(0)

    sum = 0
    for i in range(len(name)):
        sum += abs(label[i] - y_score[i])
        if (abs(label[i] - y_score[i]) <= 3):
            acc += 1
    acc = acc / float(m)
    sum = sum / float(m)
    print "Accuracy = " + str(acc)
    print "ave_diff = " + str(sum)
    with open('pro_log.csv', 'w') as csvfile:
        fieldnames = ['Name', 'Profession', 'human_score', 'log_map', 'linear_map']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(m):
            writer.writerow({'Name': name[i], 'Profession': prof[i], 'human_score' : y_score[i], 'log_map' : label[i], 'linear_map' : label1[i]})

    label = []
    label1 = []
    acc = 0
    print "Decision Tree:"
    for i in range(m):
        filename = "Classifier/Profession/DecisionTree/"+prof[i]+".pkl"
        try:
            clf_logistic = joblib.load(filename)
            l = clf_logistic.predict_proba(feature[i, :].reshape(1, -1))[0, 1]
            l1 = int(l * 8)
            l = math.pow(2, 7) * l + 1
            l = math.log(l, 2)
            l = int(l)
            label.append(l)
            label1.append(l1)
        except IOError:
            print "Cannot Find"
            label.append(0)
            label1.append(0)
    sum = 0
    for i in range(m):
        sum += abs(label[i] - y_score[i])
        if (abs(label[i] - y_score[i]) <= 3):
            acc += 1
    acc = acc / float(m)
    sum = sum / float(m)
    print "Accuracy = " + str(acc)
    print "ave_diff = " + str(sum)
    with open('pro_tree.csv', 'w') as csvfile:
        fieldnames = ['Name', 'Profession', 'human_score', 'log_map', 'linear_map']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(m):
            writer.writerow({'Name': name[i], 'Profession': prof[i], 'human_score' : y_score[i], 'log_map' : label[i], 'linear_map' : label1[i]})

    label = []
    label1 = []
    acc = 0
    print "Random Forest:"
    for i in range(m):
        filename = "Classifier/Profession/RandomForest/" + prof[i] + ".pkl"
        try:
            clf_logistic = joblib.load(filename)
            l = clf_logistic.predict_proba(feature[i, :].reshape(1, -1))[0, 1]
            l1 = int(l * 8)
            l = math.pow(2, 7) * l + 1
            l = math.log(l, 2)
            l = int(l)
            label.append(l)
            label1.append(l1)
        except IOError:
            print "Cannot Find"
            label.append(0)
            label1.append(0)

    sum = 0
    for i in range(m):
        sum += abs(label[i] - y_score[i])
        if (abs(label[i] - y_score[i]) <= 3):
            acc += 1
    acc = acc / float(m)
    sum = sum / float(m)
    print "Accuracy = " + str(acc)
    print "ave_diff = " + str(sum)
    with open('pro_forest.csv', 'w') as csvfile:
        fieldnames = ['Name', 'Profession', 'human_score', 'log_map', 'linear_map']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(m):
            writer.writerow({'Name': name[i], 'Profession': prof[i], 'human_score' : y_score[i], 'log_map' : label[i], 'linear_map' : label1[i]})


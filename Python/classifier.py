import csv
import numpy as np
from sklearn import tree
from sklearn import linear_model
from sklearn.ensemble import RandomForestClassifier
from sklearn.externals import joblib

#Read all professions and nationality
prof = []
nation = []
with open('profession.txt', 'r') as f:
    for line in f:
        prof.append(line.strip())
with open('nationality.txt', 'r') as f:
    for line in f:
        nation.append(line.strip())

#Build all classifier
for i in prof:
    openfile = 'samples_s/Professions/' + i + '.csv'
    with open(openfile, 'rb') as f:
        reader = csv.reader(f)
        F = []
        for row in reader:
            F.append(row)
        if F == []:
            continue
        X = np.asarray(F)
        Y = X[:, 0]
        X = X[:, 1:]
        (m, n) = X.shape
        X_train = X
        y_train = Y
        X_train = X_train.astype(np.int)
        y_train = y_train.astype(np.int)

        print ("Start Training " + i)
        clf = linear_model.LogisticRegression()
        clf.fit(X_train, y_train)
        filename = 'Classifier/Profession/LogisticRegression/' + i + '.pkl'
        joblib.dump(clf, filename)

        clf = tree.DecisionTreeClassifier()
        clf.fit(X_train, y_train)
        filename = 'Classifier/Profession/DecisionTree/' + i + '.pkl'
        joblib.dump(clf, filename)

        clf = RandomForestClassifier()
        clf.fit(X_train, y_train)
        filename = 'Classifier/Profession/RandomForest/' + i + '.pkl'
        joblib.dump(clf, filename)

    print ("Finish " + i)
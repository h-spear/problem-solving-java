const fs = require('fs');
const { runBaekjoonReadmeGenerator } = require('./baekjoon/boj.js');

const baekjoon_dir = './baekjoon';
const programmers_dir = './programmers';
const leetcode_dir = './leetcode';
const hackerrank_dir = './hackerrank';
const swea_dir = './swea';
const note_dir = './my_note';
const directories = [
    baekjoon_dir,
    programmers_dir,
    leetcode_dir,
    swea_dir,
    hackerrank_dir,
];
const algMap = {
    backtracking: 'Back Tracking',
    bellmanford: 'Bellman Ford',
    binarysearch: 'Binary Search',
    bruteforce: 'Bruteforce',
    combinatorics: 'Combinatorics',
    datastructure: 'Data Structure',
    dijkstra: 'Dijkstra',
    disjointset: 'Union Find',
    divideandconquer: 'Divide and Conquer',
    dp: 'Dynamic Programming',
    floydwarshall: 'Floyd Warshall',
    graphsearch: 'Graph Traversal',
    greedy: 'Greedy',
    implementation: 'Implementation',
    kmp: 'KMP',
    lca: 'LCA(Lowest Common Ancestor)',
    math: 'Math',
    mst: 'MST(Minimum Spanning Tree)',
    prefixsum: 'Prefix Sum',
    segmenttree: 'Segment Tree',
    sorting: 'Sorting',
    string: 'String',
    topologysort: 'Topology Sort',
    tree: 'Tree',
    trie: 'Trie',
    twopointer: 'Two Pointer',
    fail: 'Fail',
};

const result = {
    baekjoon: [],
    programmers: [],
    leetcode: [],
    swea: [],
    hackerrank: [],
};
const output_file = 'readme.md';

function check_condition(p) {
    if (
        './' + p.name === programmers_dir ||
        './' + p.name === leetcode_dir ||
        './' + p.name === swea_dir ||
        './' + p.name === note_dir
    ) {
        return false;
    }
    if (!p.isDirectory()) {
        return false;
    }
    if (p.name[0] == '.') {
        return false;
    }
    return true;
}

directories.forEach((directory) => {
    fs.readdirSync(directory, { withFileTypes: true }).forEach((p) => {
        const name = p.name;
        const path = directory + '/' + name;

        if (!check_condition(p)) {
            return;
        }
        result[directory.substring(2)].push({
            name,
            length: fs.readdirSync(path).length,
        });
    });
});

runBaekjoonReadmeGenerator();

// write md file
if (!fs.existsSync(output_file)) {
    fs.openSync(output_file, 'w', 666);
}

fs.writeFileSync(output_file, '');

// 백준
let baekjoon_sum = 0;
fs.appendFileSync(
    output_file,
    '## [BAEKJOON](./baekjoon/)\n|    Algorithm    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);
result['baekjoon'].forEach((v) => {
    const { name, length } = v;
    temp = '|' + algMap[name] + '|' + length + '|\n';
    baekjoon_sum += length;
    fs.appendFileSync(output_file, temp, 'utf-8');
});
fs.appendFileSync(
    output_file,
    '| **sum** | **' + baekjoon_sum + '**|\n\n',
    'utf-8'
);
console.log('baekjoon solved ' + baekjoon_sum + '!');

// 프로그래머스
let programmers_sum = 0;
fs.appendFileSync(
    output_file,
    '## [Programmers](./programmers/)\n|    Level    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);
result['programmers'].forEach((v) => {
    const { name, length } = v;
    temp = '|' + name + '|' + length + '|\n';
    programmers_sum += length;
    fs.appendFileSync(output_file, temp, 'utf-8');
});
fs.appendFileSync(
    output_file,
    '| **sum** | **' + programmers_sum + '**|\n\n',
    'utf-8'
);
console.log('programmers solved ' + programmers_sum + '!');

// 리트코드
let leetcode_sum = 0;
fs.appendFileSync(
    output_file,
    '## [LeetCode](./leetcode/)\n|    Algorithm    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);
result['leetcode'].forEach((v) => {
    const { name, length } = v;
    temp = '|' + name + '|' + length + '|\n';
    leetcode_sum += length;
    fs.appendFileSync(output_file, temp, 'utf-8');
});
fs.appendFileSync(
    output_file,
    '| **sum** | **' + leetcode_sum + '**|\n\n',
    'utf-8'
);
console.log('leetcode solved ' + leetcode_sum + '!');

// 해커랭크
let hackerrank_sum = 0;
fs.appendFileSync(
    output_file,
    '## [HackerRank](./hackerrank/)\n|    SubDomain    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);
result['hackerrank'].forEach((v) => {
    const { name, length } = v;
    temp = '|' + name + '|' + length + '|\n';
    hackerrank_sum += length;
    fs.appendFileSync(output_file, temp, 'utf-8');
});
fs.appendFileSync(
    output_file,
    '| **sum** | **' + hackerrank_sum + '**|\n\n',
    'utf-8'
);
console.log('hackerrank solved ' + hackerrank_sum + '!');

// SWEA
let swea_sum = 0;
fs.appendFileSync(
    output_file,
    '## [SW Expert Academy](./swea/)\n|    difficulty    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);
result['swea'].forEach((v) => {
    const { name, length } = v;
    temp = '|' + name + '|' + length + '|\n';
    swea_sum += length;
    fs.appendFileSync(output_file, temp, 'utf-8');
});
fs.appendFileSync(
    output_file,
    '| **sum** | **' + swea_sum + '**|\n\n',
    'utf-8'
);
console.log('swea solved ' + swea_sum + '!');
console.log('saved successfully! ' + output_file);

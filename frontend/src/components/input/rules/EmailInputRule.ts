import noSpaceInputRule from 'components/input/rules/NoSpaceInputRule';

export default (val: string) => noSpaceInputRule(val) === true && val.indexOf('@') !== -1 && !val.endsWith('@');


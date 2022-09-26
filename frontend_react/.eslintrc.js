module.exports = {
  env: {
    browser: true,
    es2021: true
  },
  extends: [
    "plugin:react/recommended",
    "eslint:recommended",
    "airbnb",
    "eslint-config-prettier"
  ],
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: "latest",
    sourceType: "module"
  },
  plugins: ["react"],
  rules: {
    "no-param-reassign": [
      "error",
      { props: true, ignorePropertyModificationsFor: ["state"] }
    ],
    "import/no-unresolved": 0,
    "arrow-body-style": "off",
    "object-shorthand": "off",
    "no-console": "off",
    "react/prop-types": "off",
    "react/self-closing-comp": "off",
    "react/function-component-definition": "off",
    "react/destructuring-assignment": "off",
    "react/no-array-index-key": "off",
    "global-require": "off",
    "no-plusplus": "off",
    "prefer-template": "off",
    "jsx-a11y/alt-text": "off",
    "spaced-comment": "off",
    "default-param-last": "off",
    "no-unreachable": "off",
    "no-unused-vars": "off",
    "operator-assignment": "off",
    "import/prefer-default-export": "off",
    "no-lone-blocks": "off",
    "react/jsx-no-useless-fragment": "off"
  },
  settings: {
    "import/resolver": {
      alias: {
        map: [
          // ["@components", "./src/components"],
          // ["@screens", "./src/screens"],
          ["@styles", "./src/assets/styles"],
          ["@images", "./src/assets/images"]
          // ["@utils", "./src/utils"]
        ],
        extensions: [".ts", ".js", ".jsx", ".json", ".svg"]
      }
    }
  }
};
